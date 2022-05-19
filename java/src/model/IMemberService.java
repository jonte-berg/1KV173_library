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
     * Update member by ID
     * @param memberID,member
     */

    void updateMember(int memberID,Member member); //om vi ska ha uppdatera, behöver vi diskutera lite hur (förmodligen genom att skicka member modell i param)


    void deleteMember(int memberID);



}
