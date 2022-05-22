package model;


import java.util.ArrayList;

public class MemberManager implements IMemberManager {

    MemberService service = null;

    public MemberManager(MemberService service) {
        this.service = service;
    }

    public MemberManager() {
    }

    @Override
    public boolean addMember(Member newMember) {

        service.addMember(newMember);

        return false;
    }

    @Override
    public boolean deleteMember(int memberID) {

        if (service.getTheMember(memberID) == null)
            return false;
        else
            service.deleteMember(memberID);

        return true;
    }


    @Override
    public boolean searchForMember(int memberID) {

        ArrayList<Member> allMembers = service.getAllMembers();

        for (Member member : allMembers) {
            if (member.getId() == memberID) {
                return true;
            } else
                return false;
        }
        return false;
    }

}
