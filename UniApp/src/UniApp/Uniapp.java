/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UniApp;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Cyclo
 */
@Entity
@Table(name = "UNIAPP")
@NamedQueries({
    @NamedQuery(name = "Uniapp.findAll", query = "SELECT u FROM Uniapp u"),
    @NamedQuery(name = "Uniapp.findByName", query = "SELECT u FROM Uniapp u WHERE u.name = :name"),
    @NamedQuery(name = "Uniapp.findByWebPage", query = "SELECT u FROM Uniapp u WHERE u.webPage = :webPage"),
    @NamedQuery(name = "Uniapp.findByState", query = "SELECT u FROM Uniapp u WHERE u.state = :state"),
    @NamedQuery(name = "Uniapp.findByCode", query = "SELECT u FROM Uniapp u WHERE u.code = :code"),
    @NamedQuery(name = "Uniapp.findByCountry", query = "SELECT u FROM Uniapp u WHERE u.country = :country"),
    @NamedQuery(name = "Uniapp.findByDomain", query = "SELECT u FROM Uniapp u WHERE u.domain = :domain"),
    @NamedQuery(name = "Uniapp.findByPhone", query = "SELECT u FROM Uniapp u WHERE u.phone = :phone"),
    @NamedQuery(name = "Uniapp.findByViews", query = "SELECT u FROM Uniapp u WHERE u.views = :views"),
    @NamedQuery(name = "Uniapp.findByComments", query = "SELECT u FROM Uniapp u WHERE u.comments = :comments")})
public class Uniapp implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @Column(name = "WEB_PAGE")
    private String webPage;
    @Basic(optional = false)
    @Column(name = "STATE")
    private String state;
    @Basic(optional = false)
    @Column(name = "CODE")
    private String code;
    @Basic(optional = false)
    @Column(name = "COUNTRY")
    private String country;
    @Basic(optional = false)
    @Column(name = "DOMAIN")
    private String domain;
    @Column(name = "PHONE")
    private String phone;
    @Basic(optional = false)
    @Column(name = "VIEWS")
    private int views;
    @Column(name = "COMMENTS")
    private String comments;

    public Uniapp() {
    }

    public Uniapp(String name) {
        this.name = name;
    }

    public Uniapp(String name, String webPage, String state, String code, String country, String domain, int views) {
        this.name = name;
        this.webPage = webPage;
        this.state = state;
        this.code = code;
        this.country = country;
        this.domain = domain;
        this.views = views;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebPage() {
        return webPage;
    }

    public void setWebPage(String webPage) {
        this.webPage = webPage;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (name != null ? name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Uniapp)) {
            return false;
        }
        Uniapp other = (Uniapp) object;
        if ((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UniApp.Uniapp[ name=" + name + " ]";
    }
    
}
