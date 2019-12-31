/*
package com.autodoc.model.models.employee;

import com.autodoc.model.enums.SearchType;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "skillCategory")
@Setter
@Getter
@ToString
public class SkillCategory {

    public static Map<String, SearchType> getSearchField() {
        return SEARCH_FIELD;
    }

    public static final Map<String, SearchType> SEARCH_FIELD = createMap();

    private static Map<String, SearchType> createMap() {
        Map<String, SearchType> result = new HashMap<>();
        result.put("NAME", SearchType.STRING);
        result.put("ID", SearchType.INTEGER);
        return Collections.unmodifiableMap(result);
    }

    // Constructor


    public SkillCategory() {
    }

    public SkillCategory(String name) {
        this.name = name;
    }




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @OneToMany(mappedBy = "skillCategory", cascade = CascadeType.REMOVE)
    private List<Skill> skills;

    @NonNull
    private String name;
}
*/
