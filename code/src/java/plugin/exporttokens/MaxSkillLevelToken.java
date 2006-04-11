/*
 * MaxSkillLevelToken.java
 * Copyright 2003 (C) Devon Jones <soulcatcher@evilsoft.org>
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.     See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 *
 * Created on December 15, 2003, 12:21 PM
 *
 * Current Ver: $Revision$
 * Last Editor: $Author$
 * Last Edited: $Date$
 *
 */
package plugin.exporttokens;

import pcgen.core.PlayerCharacter;
import pcgen.core.SkillUtilities;
import pcgen.io.ExportHandler;
import pcgen.io.exporttoken.Token;

/**
 * MAXSKILLLEVEL token
 */
public class MaxSkillLevelToken extends Token
{
	/** Token name */
	public static final String TOKENNAME = "MAXSKILLLEVEL";

	/**
	 * @see pcgen.io.exporttoken.Token#getTokenName()
	 */
	public String getTokenName()
	{
		return TOKENNAME;
	}

	//TODO: This should really be in the skill token.
	/**
	 * @see pcgen.io.exporttoken.Token#getToken(java.lang.String, pcgen.core.PlayerCharacter, pcgen.io.ExportHandler)
	 */
	public String getToken(String tokenSource, PlayerCharacter pc, ExportHandler eh)
	{
		return getMaxSkillLevelToken(pc) + "";
	}

	/**
	 * Get the Max Skill level token value
	 * @param pc
	 * @return Max Skill level token value
	 */
	public static int getMaxSkillLevelToken(PlayerCharacter pc)
	{
		return SkillUtilities.maxClassSkillForLevel(pc.getTotalLevels(), pc).intValue();
	}
}
