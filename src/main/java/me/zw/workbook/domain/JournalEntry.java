package me.zw.workbook.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import me.zw.workbook.util.JsonDateSerializer;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "entry")
@Setter
@Getter
@ToString
@NoArgsConstructor
public class JournalEntry {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @Getter(AccessLevel.NONE)
    private Date created;

    private String summary;

    @Transient
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public JournalEntry(String title, String summary, String date) throws ParseException {
        this.title = title;
        this.summary = summary;
        this.created = format.parse(date);
    }

    @JsonSerialize(using= JsonDateSerializer.class)
    public Date getCreated(){
        return created;
    }

    @JsonIgnore
    public String getCreatedAsShort(){
        return format.format(created);
    }

}
