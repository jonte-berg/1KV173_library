package model;

import java.time.LocalDate;

public interface IMemberManager {

    /**
     * Add member
     * @param newMember
     * @return boolean
     */

    boolean addMember(Member newMember);

    /**
     * Delete member
     * @param memberID
     * @return boolean
     */

    boolean deleteMember(int memberID);

    /**
     * Search member
     * @param memberID
     * @return boolean
     */

    boolean searchForMember(int memberID);




    /*
    Om ett lån är försenat plussar issueFine på 1 på warnings.
    @para membersID
    @return void
     */

    boolean issueFine(int membersID);




    boolean suspendMember(int membersID);



}
