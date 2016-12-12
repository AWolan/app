package com.amw.app.service;

import com.amw.app.dto.AccountDTO;
import com.amw.app.dto.CategoryDTO;
import com.amw.app.dto.PaymentDTO;
import com.amw.app.dto.PersonDTO;
import com.amw.app.model.*;
import com.amw.app.reporitory.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PaymentFilterService paymentFilterService;

    @Override
    public List<PaymentDTO> getListBy(Long filterId, LocalDateTime from, LocalDateTime to) {
        PaymentFilter filter = paymentFilterService.getById(filterId);

        Assert.notNull(filter, "Filter not found.");

        List<Payment> paymentList = paymentRepository.getListBy(filter, from, to);

        return paymentList.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    private PaymentDTO convert(Payment payment) {
        PaymentDTO paymentDTO = new PaymentDTO();

        AccountDTO source = convert(payment.getSource());
        AccountDTO target = convert(payment.getTarget());
        List<CategoryDTO> categoryList = payment.getCategoryList().stream()
                .map(this::convert)
                .collect(Collectors.toList());

        paymentDTO.setId(payment.getId());
        if (payment.getGroup() != null) {
            paymentDTO.setGroupName(payment.getGroup().getName());
        }
        paymentDTO.setSource(source);
        paymentDTO.setTarget(target);
        paymentDTO.setAmount(payment.getAmount());
        paymentDTO.setCurrency(payment.getCurrency());
        paymentDTO.setName(payment.getName());
        paymentDTO.setDetails(payment.getDetails());
        paymentDTO.setExecutionDate(payment.getExecutionDate());
        paymentDTO.setPlanDate(payment.getPlanDate());
        paymentDTO.setCategoryList(categoryList);

        return paymentDTO;
    }

    private AccountDTO convert(Account account) {
        AccountDTO accountDTO = new AccountDTO();

        PersonDTO owner = convert(account.getOwner());

        accountDTO.setId(account.getId());
        accountDTO.setName("test");
        if (account.getNumber() != null) {
            accountDTO.setAccountNumber(account.getNumber().getFullNumber(false));
        }
        accountDTO.setCurrency(account.getCurrency());
        accountDTO.setOwner(owner);

        return accountDTO;
    }

    private CategoryDTO convert(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());

        return categoryDTO;
    }

    private PersonDTO convert(Person person) {
        PersonDTO personDTO = new PersonDTO();

        String fullName = person.isCompany() ? person.getCompanyName() : String.format("%s %s", person.getFirstName(), person.getLastName());

        personDTO.setId(person.getId());
        personDTO.setFullName(fullName);

        return personDTO;
    }

//    @Override
//    public List<Payment> getListBy(Long filterId, LocalDateTime from, LocalDateTime to) {
//        return paymentRepository.getListBy(filterId, from, to);
//    }

}
