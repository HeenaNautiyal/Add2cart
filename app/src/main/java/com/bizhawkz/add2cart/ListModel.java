package com.bizhawkz.add2cart;

/**
 * Created by Heena on 4/5/2017.
 */
public class ListModel {
    private  String CompanyName="";
    private  String Image="";
    private  String Url="";
    private boolean isRowSelected;
     private String  Name="";

    public void setCompanyName(String CompanyName)
    {
        this.CompanyName = CompanyName;
    }

    public void setImage(String Image)
    {
        this.Image = Image;
    }

    public void setUrl(String Url)
    {
        this.Url = Url;
    }

    /*********** Get Methods ****************/

    public String getCompanyName()
    {
        return this.CompanyName;
    }

    public String getImage()
    {
        return this.Image;
    }

    public String getUrl()
    {
        return this.Url;
    }

    public void setIsRowSelected(boolean isRowSelected) {
        this.isRowSelected = isRowSelected;
    }

    public boolean getIsRowSelected() {
        return isRowSelected;
    }

    public void setName(String Name) {
        this.Name=Name;
    }


    public String getName() {
        return this.Name;
    }
}
