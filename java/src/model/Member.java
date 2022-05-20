package model;

import java.util.List;

public class Member {

    int id;
    String sName;
    String lName;
    List<Loan> loans;
    int suspended;
    int maxLoans;
    int warnings;

    public Member() {
    }

    public Member(int id, String sName, String lName, List<Loan> loans, int suspended, int maxLoans, int warnings) {
        this.id = id;
        this.sName = sName;
        this.lName = lName;
        this.loans = loans;
        this.suspended = suspended;
        this.maxLoans = maxLoans;
        this.warnings = warnings;
    }

    public Member(int id, String sName, String lName, int suspended, int maxLoans, int warnings) {
        this.id = id;
        this.sName = sName;
        this.lName = lName;
        this.suspended = suspended;
        this.maxLoans = maxLoans;
        this.warnings = warnings;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    public int isSuspended() {
        return suspended;
    }

    public void setSuspended(int suspended) {
        this.suspended = suspended;
    }

    public int getMaxLoans() {
        return maxLoans;
    }

    public void setMaxLoans(int maxLoans) {
        this.maxLoans = maxLoans;
    }

    public int getWarnings() {
        return warnings;
    }

    public void setWarnings(int warnings) {
        this.warnings = warnings;
    }

    @Override
    public String toString() {
        return " Member {" +
                "id=" + id +
                ", sName='" + sName + '\'' +
                ", lName='" + lName + '\'' +
                ", suspended=" + suspended +
                ", maxLoans=" + maxLoans +
                ", warnings=" + warnings +
                '}';
    }
}
