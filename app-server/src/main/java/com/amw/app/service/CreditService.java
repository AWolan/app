package com.amw.app.service;


import com.amw.app.dto.CreditDTO;
import com.amw.app.dto.InstallmentDTO;

import java.util.List;

public interface CreditService {

    CreditDTO generateEqual(CreditDTO credit);

    CreditDTO generateDsc(CreditDTO credit);
}
