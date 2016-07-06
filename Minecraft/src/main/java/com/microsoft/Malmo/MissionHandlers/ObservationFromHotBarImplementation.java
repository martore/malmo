// --------------------------------------------------------------------------------------------------
//  Copyright (c) 2016 Microsoft Corporation
//  
//  Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
//  associated documentation files (the "Software"), to deal in the Software without restriction,
//  including without limitation the rights to use, copy, modify, merge, publish, distribute,
//  sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
//  furnished to do so, subject to the following conditions:
//  
//  The above copyright notice and this permission notice shall be included in all copies or
//  substantial portions of the Software.
//  
//  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
//  NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
//  NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
//  DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
//  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
// --------------------------------------------------------------------------------------------------

package com.microsoft.Malmo.MissionHandlers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.item.ItemStack;

import com.google.gson.JsonObject;
import com.microsoft.Malmo.MissionHandlerInterfaces.IObservationProducer;
import com.microsoft.Malmo.Schemas.MissionInit;

/** Simple IObservationProducer class that returns a list of what is in the "hotbar".
 */
public class ObservationFromHotBarImplementation extends HandlerBase implements IObservationProducer
{
	@Override
	public void prepare(MissionInit missionInit) {}

	@Override
	public void cleanup() {}

	@Override
    public void writeObservationsToJSON(JsonObject json, MissionInit missionInit)
    {
        for (int i = 0; i < 9; i++)
        {
        	EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
            ItemStack is = player.inventory.getStackInSlot(i);
            if (is != null)
            {
                json.addProperty("Hotbar_" + i + "_size", is.stackSize);
                json.addProperty("Hotbar_" + i + "_item", is.getItem().getUnlocalizedName());
            }
        }
        System.out.println(json.toString());
    }
}