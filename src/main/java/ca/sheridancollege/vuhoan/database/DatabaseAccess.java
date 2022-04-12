package ca.sheridancollege.vuhoan.database;

import ca.sheridancollege.vuhoan.beans.Contact;
import ca.sheridancollege.vuhoan.beans.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class DatabaseAccess {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private NamedParameterJdbcTemplate jdbc;

    public Contact findContactById(Long id) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "SELECT * FROM contact where id=:id";
        parameters.addValue("id", id);
        ArrayList<Contact> contact = (ArrayList<Contact>)jdbc.query(query, parameters, new BeanPropertyRowMapper<Contact>(Contact.class));
        if (contact.size()>0)
            return contact.get(0);
        else
            return null;
    }

    public List<Contact> listALL() {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "SELECT * FROM contact";
        return jdbc.query(query, parameters, new BeanPropertyRowMapper<Contact>(Contact.class));
    }

    public void addContact(Contact contact){
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "insert into contact "
                + "(name, phone, address, email, role) "
                + "values (:name, :phone, :address, :email, :role)";
        namedParameters.addValue("name", contact.getName());
        namedParameters.addValue("phone", contact.getPhone());
        namedParameters.addValue("address", contact.getAddress());
        namedParameters.addValue("email", contact.getEmail());
        namedParameters.addValue("role", contact.getRole());
        jdbc.update(query, namedParameters);
    }

    public List<String> getRolesById(Long userId) {

        ArrayList<String> roles = new ArrayList<String>();
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "select user_role.userId, sec_role.roleName "
                + "FROM user_role, sec_role "
                + "WHERE user_role.roleId=sec_role.roleId "
                + "AND userId=:userId";
        parameters.addValue("userId", userId);
        List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);
        for (Map<String, Object> row : rows) {
            roles.add((String)row.get("roleName"));
        }
        return roles;
    }






    public User findUserAccount(String email) {

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "SELECT * FROM sec_user where email=:email";
        parameters.addValue("email", email);
        ArrayList<User> users = (ArrayList<User>)jdbc.query(query, parameters, new
                BeanPropertyRowMapper<User>(User.class));
        if (users.size()>0)
            return users.get(0);
        else
            return null;


    }

    public void registerUser(String email, String password){
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "insert into sec_user "
                + "(email, encryptedPassword, enabled) "
                + "values (:email, :encryptedPassword, 1)";
        namedParameters.addValue("email", email);
        namedParameters.addValue("encryptedPassword", passwordEncoder.encode(password));
        jdbc.update(query, namedParameters);
    }

    public void addRole(Long userId, Long roleId){
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "insert into user_role(userId, roleId) "
                + "values (:userId, :roleId)";
        namedParameters.addValue("userId", userId);
        namedParameters.addValue("roleId", roleId);
        jdbc.update(query, namedParameters);
    }

    public void deleteContactById(Long id){
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "delete from contact where id = :id";
        namedParameters.addValue("id", id);
        jdbc.update(query, namedParameters);
    }


    public void updateContact(Contact contact){
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "update contact set name = :name " +
                ", phone = :phone, address = :address, "
                + "email = :email, role = :role " +
                "where id = :id";
        namedParameters.addValue("id", contact.getId());
        namedParameters.addValue("name", contact.getName());
        namedParameters.addValue("phone", contact.getPhone());
        namedParameters.addValue("address", contact.getAddress());
        namedParameters.addValue("email", contact.getEmail());
        namedParameters.addValue("role", contact.getRole());
        jdbc.update(query, namedParameters);
    }

}




