package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;
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
    void addMember() {
        Member newMember = new Member (1634,"John","Doe",0,3,0);

    }

    @Test
    void deleteMember() {
    }

    @Test
    void updateMember() {

    }

    @Test
    void suspendMember() {
    }

    @Test
    void searchForMember() {
    }


}