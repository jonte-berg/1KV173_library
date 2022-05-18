package model;

import java.time.LocalDate;

public class MemberManager implements IMemberManager {

    @Override
    public boolean addMember(Member newMember) {
        return false;
    }

    @Override
    public boolean deleteMember(int memberID) {
        return false;
    }

    @Override
    public boolean suspendMember(LocalDate start, LocalDate end) {
        return false;
    }

    @Override
    public boolean searchForMember(int memberID) {
        return false;
    }
}
