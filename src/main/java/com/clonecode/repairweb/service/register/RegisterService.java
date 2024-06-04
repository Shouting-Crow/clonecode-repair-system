package com.clonecode.repairweb.service.register;

import com.clonecode.repairweb.domain.login.Admin;
import com.clonecode.repairweb.domain.login.Member;
import com.clonecode.repairweb.domain.login.Repairman;
import com.clonecode.repairweb.domain.login.User;
import com.clonecode.repairweb.form.AdminRegisterForm;
import com.clonecode.repairweb.form.MemberRegisterForm;
import com.clonecode.repairweb.form.RepairmanRegisterForm;

public interface RegisterService {
    Admin registerAdmin(AdminRegisterForm form);
    Member registerMember(MemberRegisterForm form);
    Repairman registerRepairman(RepairmanRegisterForm form);
}
