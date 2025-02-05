package trial_project_Intrasoft.trial_project_Intrasoft.model;

// Beneficiary Model Class Definition
public class Beneficiary {
    private int beneficiaryId;
    private String firstName;
    private String lastName;

    // Constructor
    public Beneficiary(int beneficiaryID, String firstName, String lastName){
        this.beneficiaryId = beneficiaryID;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Get Functions
    public int getBeneficiaryId() {
        return beneficiaryId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    /* Set Functions won't be necessary for this trial project's requirements
       But this is where I would implement them if I had the need to do so
     */
}