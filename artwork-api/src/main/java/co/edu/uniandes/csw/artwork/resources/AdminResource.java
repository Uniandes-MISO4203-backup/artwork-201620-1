/*
The MIT License (MIT)

Copyright (c) 2015 Los Andes University

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package co.edu.uniandes.csw.artwork.resources;

import co.edu.uniandes.csw.auth.provider.StatusCreated;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import co.edu.uniandes.csw.artwork.api.IArtistLogic;
import co.edu.uniandes.csw.artwork.dtos.detail.ArtistDetailDTO;
import co.edu.uniandes.csw.artwork.entities.ArtistEntity;
import co.edu.uniandes.csw.auth.model.UserDTO;
import co.edu.uniandes.csw.auth.stormpath.Utils;
import com.stormpath.sdk.account.Account;
import com.stormpath.sdk.group.Group;
import com.stormpath.sdk.tenant.Tenant;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import static co.edu.uniandes.csw.auth.stormpath.Utils.*;
import com.stormpath.sdk.account.AccountList;

/**
 * URI: admins/
 * @generated
 */
@Path("/admins")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AdminResource {
    private static final String CLIENT_HREF = "https://api.stormpath.com/v1/groups/8hCxfQfGQ1EvhrCX9yXsL";
    private static final String ARTIST_HREF = "https://api.stormpath.com/v1/groups/K4yTGg11sCUoGBbJe0GJ3";
    private static final String ADMIN_HREF = "https://api.stormpath.com/v1/groups/7luSBhdqfQi2FUjUZAIhp7";  
    
    @Context private HttpServletResponse response;
    @Context private HttpServletRequest req;
    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;
    

    /**
     * Obtiene la lista de los registros de Users
     *
     * @return Colección de objetos de UserDTO
     * @generated
     */
    @GET
    public List<UserDTO> getUsers() {
        List<UserDTO> userDTOs = new ArrayList<UserDTO>();
        UserDTO userDTO;
        Subject currentUser = SecurityUtils.getSubject();
//        StringBuilder stringBuilder = new StringBuilder("");
        if (currentUser != null){
            AccountList accounts = getApplication().getAccounts();
            for (Account account : accounts) {
                userDTO = new UserDTO(account);
                userDTOs.add(userDTO);
//                for (String rol : userDTO.getRoles()){
//                    stringBuilder.append(rol+", ");
//                }
                
                userDTO.setSurName(String.join(", ", userDTO.getRoles()));
//                userDTO.setSurName(stringBuilder.toString().substring(0,stringBuilder.toString().lastIndexOf(", ")));
//                System.out.println("Cuenta: "+account.getUsername());
            }
        }
        return userDTOs;
    }

}
