/*
 * GNU LESSER GENERAL PUBLIC LICENSE
 *                       Version 3, 29 June 2007
 *
 * Copyright (C) 2007 Free Software Foundation, Inc. <http://fsf.org/>
 * Everyone is permitted to copy and distribute verbatim copies
 * of this license document, but changing it is not allowed.
 *
 * You can view LICENCE file for details. 
 *
 * @author The Dragonet Team
 */
package org.dragonet.proxy.network.translator.pc;

import com.github.steveice10.mc.protocol.packet.ingame.server.world.ServerUpdateTileEntityPacket;
import org.dragonet.proxy.network.UpstreamSession;
import org.dragonet.proxy.network.translator.ItemBlockTranslator;
import org.dragonet.proxy.network.translator.IPCPacketTranslator;
import org.dragonet.protocol.PEPacket;
import org.dragonet.protocol.packets.BlockEntityDataPacket;
import org.dragonet.common.maths.BlockPosition;

public class PCUpdateTileEntityPacketTranslator implements IPCPacketTranslator<ServerUpdateTileEntityPacket> {

    public PEPacket[] translate(UpstreamSession session, ServerUpdateTileEntityPacket originalPacket) {

        BlockEntityDataPacket data = new BlockEntityDataPacket();
        data.blockPosition = new BlockPosition(originalPacket.getPosition());
        data.tag = ItemBlockTranslator.translateBlockEntityToPE(originalPacket.getNBT());
        return new PEPacket[]{data};
    }
}
