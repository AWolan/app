CREATE TABLE `t_person` (
  `id` bigint(20) NOT NULL,
  `company_name` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `is_company` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `t_account_number` (
  `id` bigint(20) NOT NULL,
  `iban` varchar(255) DEFAULT NULL,
  `bank_number` bigint(20) DEFAULT NULL,
  `account_number` bigint(20) DEFAULT NULL,
  `control_sum` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `t_account` (
  `id` bigint(20) NOT NULL,
  `number_id` bigint(20) DEFAULT NULL,
  `owner_id` bigint(20) DEFAULT NULL,
  `currency` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_a_owner_id` (`number_id`),
  KEY `fk_a_account_number_id` (`owner_id`),
  CONSTRAINT `fk_a_owner_id` FOREIGN KEY (`owner_id`) REFERENCES `t_person` (`id`),
  CONSTRAINT `fk_a_account_number_id` FOREIGN KEY (`number_id`) REFERENCES `t_account_number` (`id`)
);

CREATE TABLE `t_payment_group` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `t_category` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `t_payment` (
  `id` bigint(20) NOT NULL,
  `group_id` bigint(20) DEFAULT NULL,
  `source_id` bigint(20) DEFAULT NULL,
  `target_id` bigint(20) DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `currency` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `details` varchar(255) DEFAULT NULL,
  `execution_date` datetime DEFAULT NULL,
  `plan_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_p_group_id` (`group_id`),
  KEY `fk_p_source_id` (`source_id`),
  KEY `fk_p_target_id` (`target_id`),
  CONSTRAINT `fk_p_group_id` FOREIGN KEY (`group_id`) REFERENCES `t_payment_group` (`id`),
  CONSTRAINT `fk_p_source_id` FOREIGN KEY (`source_id`) REFERENCES `t_account` (`id`),
  CONSTRAINT `fk_p_target_id` FOREIGN KEY (`target_id`) REFERENCES `t_account` (`id`)
);

CREATE TABLE `t_payment_category` (
  `payment_id` bigint(20) NOT NULL,
  `category_id` bigint(20) NOT NULL,
  KEY `fk_pc_payment_id` (`payment_id`),
  KEY `fk_pc_category_id` (`category_id`),
  CONSTRAINT `fk_pc_payment_id` FOREIGN KEY (`payment_id`) REFERENCES `t_payment` (`id`),
  CONSTRAINT `fk_pc_category_id` FOREIGN KEY (`category_id`) REFERENCES `t_category` (`id`)
);

CREATE TABLE `t_payment_filter` (
  `id` bigint(20) NOT NULL,
  `owner_id` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `direction` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_pf_owner_id` FOREIGN KEY (`owner_id`) REFERENCES `t_person` (`id`)
);

CREATE TABLE `t_payment_filter_category` (
  `payment_filter_id` bigint(20) NOT NULL,
  `category_id` bigint(20) NOT NULL,
  KEY `fk_pfc_payment_filter_id` (`payment_filter_id`),
  KEY `fk_pfc_category_id` (`category_id`),
  CONSTRAINT `fk_pfc_payment_filter_id` FOREIGN KEY (`payment_filter_id`) REFERENCES `t_payment_filter` (`id`),
  CONSTRAINT `fk_pfc_category_id` FOREIGN KEY (`category_id`) REFERENCES `t_category` (`id`)
);
