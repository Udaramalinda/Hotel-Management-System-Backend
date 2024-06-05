package com.suntravels.backend.SunTravelsBackend.DTO;

public class AgentDTO
{
    private String email;
    private String name;
    private String password;
    private boolean isAdmin;

    public AgentDTO() {}

    public AgentDTO( String email, String name, String password, boolean isAdmin )
    {
        this.email = email;
        this.name = name;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail( String email )
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword( String password )
    {
        this.password = password;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public boolean isAdmin()
    {
        return isAdmin;
    }

    public void setIsAdmin( boolean admin )
    {
        isAdmin = admin;
    }
}
