/*      Kurs: 1IK173
        Projekt
        Kursdeltagare: Dennis Schill, Robin Sjöberg, Jonathan Berg, Konstantinos Karidas
        Termin och datum: VT22 30/5   */

package model;



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

    Member searchForMemberInfo(int memberID);


}
