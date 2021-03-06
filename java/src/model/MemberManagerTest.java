/*      Kurs: 1IK173
        Projekt
        Kursdeltagare: Dennis Schill, Robin Sjöberg, Jonathan Berg, Konstantinos Karidas
        Termin och datum: VT22 30/5   */

package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class MemberManagerTest {

    MemberService memberService = mock(MemberService.class);
    MemberManager mg = new MemberManager();

    @BeforeEach
    void setUp() {
        System.out.println("Setting it up! ....");
         mg = new MemberManager(memberService);
    }

    @AfterEach
    void tearDown() {
        System.out.println("Running: tearDown! ....");
        mg = null;
        assertNull(mg);
    }

    @Test
    @DisplayName("This test should provide a true boolean")
    void addMemberPositive() throws SQLException {
        //Setup
        Member nw1 = new Member (10000, "John", "Doe",0,3,0);


        //Assert and confirm
        when(memberService.addMember(any(Member.class))).thenReturn(true);
        assertEquals(true, mg.addMember(nw1));
    }

    @Test
    @DisplayName("This test should provide a false boolean")
    void addMemberNegative() throws SQLException {

        //Setup
        Member nw1 = new Member ();


        //Assert and confirm
        when(memberService.addMember(any(Member.class))).thenReturn(false);
        assertEquals(false, mg.addMember(nw1));

    }

    @Test
    @DisplayName("This test should provide a true boolean")
    void deleteMember_Positive() {


        when(memberService.getTheMember(1))
                .thenReturn( new Member(1, "jont", "berg", 0,3,0));
        when(memberService.getTheMember(2))
                .thenReturn( new Member(2, "Göran", "Persson", 0,3,0));

        //1 finns, 2 finns
        assertEquals(mg.deleteMember(1),true);
        assertEquals(mg.deleteMember(2),true);



    }
    @Test
    @DisplayName("This test should provide a false boolean")
    void deleteMember_Negative() {


        when(memberService.getTheMember(3))
                .thenReturn( null);
        when(memberService.getTheMember(4))
                .thenReturn( null);

        //3 finns inte, 4 finns inte
        assertEquals(false,mg.deleteMember(3));
        assertEquals(false,mg.deleteMember(4));


    }

    @Test
    @DisplayName("This test should provide a true boolean")
    void searchForMemberPositive() {

        //  Setup
        ArrayList<Member> mocks = new ArrayList<Member>();

        mocks.add(new Member(5002, "John", "Doe", 0, 3, 0));
        mocks.add(new Member(5003, "Bertil", "Doe", 0, 3, 0));
        mocks.add(new Member(5004, "Magda", "Doe", 0, 3, 0));


        // Assert and confirm
        when(memberService.getAllMembers()).thenReturn(mocks);

        assertTrue(mg.searchForMember(5002));

    }

    @Test
    @DisplayName("This test should provide a false boolean")
    void searchForMemberNegative() {

        //  Setup
        ArrayList<Member> mocks = new ArrayList<Member>();

        mocks.add(new Member(5002, "John", "Doe", 0, 3, 0));
        mocks.add(new Member(5003, "Bertil", "Doe", 0, 3, 0));
        mocks.add(new Member(5004, "Magda", "Doe", 0, 3, 0));


        // Assert and confirm
        when(memberService.getAllMembers()).thenReturn(mocks);

        assertFalse(mg.searchForMember(5005));

    }


}