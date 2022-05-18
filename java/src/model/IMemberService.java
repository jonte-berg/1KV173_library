package model;



public interface IMemberService {


    /**
     * Get all members
     * @return Member[]
     */

    Member[] getAllMembers();

    /**
     * Get specific member by ID
     * @param memberID
     * @return Member
     */

    Member getTheMember(int memberID);

    /**
     * Update member by ID
     * @param memberID
     * @return boolean
     */

    boolean updateMember(int memberID);

}