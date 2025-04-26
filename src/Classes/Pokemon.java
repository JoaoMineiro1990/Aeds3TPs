package Classes;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Pokemon {
    private int numberPokedex;
    private String name;
    private String type1;
    private String type2;
    private String abilities;
    private int hp;
    private int att;
    private int def;
    private int spa;
    private int spd;
    private int spe;
    private int bst;
    private long dataEpoch;

    public long getDataEpoch() {
        return dataEpoch;
    }

    public void setDataEpoch(long dataEpoch) {
        this.dataEpoch = dataEpoch;
    }

    public int getNumberPokedex() {
        return numberPokedex;
    }

    public void setNumberPokedex(int numberPokedex) {
        this.numberPokedex = numberPokedex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public String getAbilities() {
        return abilities;
    }

    public void setAbilities(String abilities) {
        this.abilities = abilities;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAtt() {
        return att;
    }

    public void setAtt(int att) {
        this.att = att;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getSpa() {
        return spa;
    }

    public void setSpa(int spa) {
        this.spa = spa;
    }

    public int getSpd() {
        return spd;
    }

    public void setSpd(int spd) {
        this.spd = spd;
    }

    public int getSpe() {
        return spe;
    }

    public void setSpe(int spe) {
        this.spe = spe;
    }

    public int getBst() {
        return bst;
    }

    public void setBst(int bst) {
        this.bst = bst;
    }
    public static String epochParaData(long epoch) {
        Instant instant = Instant.ofEpochSecond(epoch);
        LocalDate data = instant.atZone(ZoneId.of("UTC")).toLocalDate();
        return data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
