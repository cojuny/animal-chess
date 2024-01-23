package hk.edu.polyu.comp.comp2021.jungle.model;

public enum Team { //Specify the team in the enum value
    Home("home"), Away("away");

    private String team;

    Team(String team) {
        this.team = team;
    }

    /**
     * 
     * @return the team value in the string format.
     */
    public String getTeam() {
        return this.team;
    }

}
