package generator.api;

import generator.graphql.Defaults;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@IdClass(value = UserDefaults.class)
@Table(name = "defaults")
public class UserDefaults implements Serializable {

    @Id private Boolean defaultBoolean;
    @Id private Integer defaultInteger;
    @Id private Double defaultFloat;
    @Id private String defaultString;
    @Id private String defaultID;
    @Id private Integer arrayLength;
    @Id private Integer enumPlace;// starts at 1

    public UserDefaults(){
        this.defaultBoolean = Defaults.BOOLEAN;
        this.defaultInteger = Defaults.INTEGER;
        this.defaultFloat = Defaults.FLOAT;
        this.defaultString = Defaults.STRING;
        this.defaultID = Defaults.ID;
        this.arrayLength = Defaults.ARRAY_LENGTH;
        this.enumPlace = Defaults.ENUM_PLACE;
    }
}