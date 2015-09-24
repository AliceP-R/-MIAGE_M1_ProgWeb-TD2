
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alpottie
 */
public class Authentification {
    
    // contient toutes les valeurs contenu dans la base
    Hashtable<String, String> res_req;  
    
    public Authentification()
    {
        this.res_req = new Hashtable<>();
        try
        {
            String url="jdbc:oracle:thin:@miage03.dmiage.u-paris10.fr:1521:MIAGE"; 
            Connection cx = DriverManager.getConnection(url, "alpottie", "classique2014PW"); 
            
            /** Création et exécution d'une requete - étape 3 & 4 */
            Statement st = cx.createStatement(); 
            ResultSet rs = st.executeQuery("SELECT * FROM LOGPWD"); 
            
            /** Enregistrement du résultat */
            while(rs.next())
            {
                String nom = rs.getString("login"); 
                String mdp = rs.getString("pwd"); 
                
                this.res_req.put(nom, mdp); 
            }
            
            /** Fermeture */
            rs.close(); // fermeture du résultat de la requête 
            st.close(); // fermeture de la requête 
            cx.close(); // fermeture de la connexion
        }
        catch(SQLException ex)
        {
            System.err.println("Erreur lors de la connexion à la base.");
            System.exit(1); 
        }
    }
    
    public boolean estReconnu(String log, String pwd)
    {
        if(res_req.containsKey(log) == true)
        {
            System.out.println("dans premier if"); 
            if(res_req.get(log).equals(pwd))
            {    
                return true; 
            }
            else 
                return false; 
        }
        else
            return false; 
    }
    
}