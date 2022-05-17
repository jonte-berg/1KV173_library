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
     * Suspend member
     * @param start
     * @param end
     * @return boolean
     */

    boolean suspendMember(LocalDate start, LocalDate end);


    /**
     * Search member
     * @param memberID
     * @return boolean
     */
    boolean searchForMember(int memberID);

}
