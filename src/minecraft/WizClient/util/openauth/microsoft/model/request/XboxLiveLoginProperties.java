/*
 * Copyright 2015-2021 Adrien 'Litarvan' Navratil
 *
 * This file is part of OpenAuth.

 * OpenAuth is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * OpenAuth is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with OpenAuth.  If not, see <http://www.gnu.org/licenses/>.
 */
package WizClient.util.openauth.microsoft.model.request;

public class XboxLiveLoginProperties
{
    private final String AuthMethod;
    private final String SiteName;
    private final String RpsTicket;

    public XboxLiveLoginProperties(String AuthMethod, String SiteName, String RpsTicket)
    {
        this.AuthMethod = AuthMethod;
        this.SiteName = SiteName;
        this.RpsTicket = RpsTicket;
    }

    public String getAuthMethod()
    {
        return AuthMethod;
    }

    public String getSiteName()
    {
        return SiteName;
    }

    public String getRpsTicket()
    {
        return RpsTicket;
    }
}
