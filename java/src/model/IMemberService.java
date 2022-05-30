/*      Kurs: 1IK173
        Projekt
        Kursdeltagare: Dennis Schill, Robin Sjöberg, Jonathan Berg, Konstantinos Karidas
        Termin och datum: VT22 30/5   */

package model;


import java.time.LocalDate;
import java.util.ArrayList;

public interface IMemberService {


    /**
     * Get all members
     * @return Member[]
     */

    ArrayList<Member>  getAllMembers();

    /**
     * Get specific member by ID
     * @param memberID
     * @return Member
     */

    Member getTheMember(int memberID);

    /**
     * Add member by member object
     * @param newMember
     * @return boolean
     */

    boolean addMember(Member newMember);

    /**
     * Delete member by ID
     * @param memberID
     * @return boolean
     */

    boolean deleteMember(int memberID);

}
