package hk.edu.polyu.comp.comp2021.jungle.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class TeamTest {

    @Test
    public void Team() {
        Team home = Team.Home;
        Team away = Team.Away;

        assert home.getTeam().equals("home");
    }

}