package esipe.fr.model;

import io.swagger.annotations.ApiModel;

import java.util.Date;
import java.util.UUID;

@ApiModel(description = "esipe.fr.model.Credentials")
public class Credentials {
    private Long usrId;
    private String login,pwd,name;
    private boolean authenticated;
    private String code;
    private UUID uuid;
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Long getUsrId() {
        return usrId;
    }

    public void setUsrId(Long usrId) {
        this.usrId = usrId;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Customer mapCustomer(String sKey){
        Customer c1 = new Customer();
        c1.setLogin(this.getLogin());
        c1.setPwd(this.getPwd());
        c1.setName(this.getName());
        c1.setsKey(sKey);
        return c1;
    }
}
