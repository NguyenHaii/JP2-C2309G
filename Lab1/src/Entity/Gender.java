package Entity;

public enum Gender {
    M("Male"), F("Female"),O("Other");
    private String genderLabel;
    Gender (String genderLabel){
        this.genderLabel = genderLabel;
    }
    public String GenderLabel(){
        return genderLabel;
    }
    public void setGenderLabel(String genderLabel){
        this.genderLabel =genderLabel;
    }
}