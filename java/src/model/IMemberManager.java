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



}
