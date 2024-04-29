/*
 * This file is part of  GottschCore.
 * Copyright (c) 2020 Mark Gottschling (gottsch)
 * 
 * All rights reserved.
 *
 * GottschCore is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GottschCore is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with GottschCore.  If not, see <http://www.gnu.org/licenses/lgpl>.
 */
package mod.gottsch.fabric.gottschcore.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.data.client.VariantSettings;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.Direction;

/**
 * 
 * @author Mark Gottschling on Jan 15, 2020
 *
 */
public class BasedBlock extends Block implements IBasedBlock {

	public BasedBlock(AbstractBlock.Settings properties) {
		super(properties);
			this.setDefaultState(this.stateManager.getDefaultState().with(BASE, Direction.UP));
	}

	/**
	 * 
	 */
	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(BASE);
	}

	/**
	 * 
	 */
	public BlockState getPlacementState(ItemPlacementContext context) {
		return this.getDefaultState().with(BASE, context.getSide());
	}

	/**
	 *
	 */
	@Override
	public BlockState rotate(BlockState state, BlockRotation rotation) {
		return state.with(BASE, rotation.rotate(getBase(state)));
	}

	/**
	 * 
	 * @param state
	 * @return
	 */
	@Override
	public Direction getBase(BlockState state) {
		return state.get(BASE);
	}
}
