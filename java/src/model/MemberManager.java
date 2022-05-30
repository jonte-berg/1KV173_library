/*      Kurs: 1IK173
        Projekt
        Kursdeltagare: Dennis Schill, Robin Sjöberg, Jonathan Berg, Konstantinos Karidas
        Termin och datum: VT22 30/5   */

package model;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

        Logger logger = LogManager.getLogger(MemberManager.class.getName());

        if (service.addMember(newMember)) {
            logger.info("A member has been added");
            return true;
        }
        else {
            logger.error("The member has failed to be added");
            return false;
        }
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
            }
        }
        return false;
    }





    @Override
    public Member searchForMemberInfo(int memberID) {

        ArrayList<Member> allMembers = service.getAllMembers();
        Member theMember;

        for (Member m : allMembers) {
            if (m.getId() == memberID) {
                theMember = new Member(m.getId(), m.getsName(), m.getlName(), m.isSuspended(), m.getMaxLoans(), m.getWarnings());
                theMember.setCurrentLoan(service.getCurrentLoans(memberID));
                return theMember;
            }
        }
        return theMember = null;
    }



}
