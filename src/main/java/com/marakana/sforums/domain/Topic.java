package com.marakana.sforums.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.QueryHint;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(
        name = "topic",
        uniqueConstraints = { @UniqueConstraint(columnNames = { "title", "forum_id" }) }
)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@NamedQueries({
    @NamedQuery(
            name = "all-topics",
            query = "from Topic order by created",
            hints = {@QueryHint(name = "org.hibernate.cacheable", value = "true")}
    ),
    @NamedQuery(
            name = "topic-by-forum-and-title",
            query = "from Topic where forum=:forum and title=:title"
    ),
    @NamedQuery(
            name = "topic-by-id-fetch-all",
            query = "select distinct t from Topic as t " + 
                    "inner join fetch t.author inner join fetch t.forum f " + 
                    "inner join fetch f.category left join fetch t.replies r " + 
                    "inner join fetch r.author where t.id=:id",
            hints = {@QueryHint(name = "org.hibernate.cacheable", value = "true")}
    )
})
public class Topic extends Post {

    private static final long serialVersionUID = -3508636277961221248L;

    private Forum forum;

    private String title;

    private List<Reply> replies = new ArrayList<>();

    @NotNull
    @ManyToOne(optional = false)
    public Forum getForum() {
        return forum;
    }

    public void setForum(Forum forum) {
        this.forum = forum;
    }

    @Size(max = 128)
    @NotEmpty
    @NotNull
    @Column(length = 128, nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @OneToMany(mappedBy = "topic", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @OrderBy("created")
    public List<Reply> getReplies() {
        return replies;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }
}
