package model;


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
