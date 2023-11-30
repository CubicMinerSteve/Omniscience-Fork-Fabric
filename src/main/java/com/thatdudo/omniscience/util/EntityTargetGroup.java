package com.thatdudo.omniscience.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.decoration.painting.PaintingEntity;
import net.minecraft.entity.mob.AmbientEntity;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.PhantomEntity;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.passive.WanderingTraderEntity;
import net.minecraft.entity.player.PlayerEntity;

public class EntityTargetGroup {
    // Using bitmasks instead of a boolean array for performance reasons
    public static final int ALL = 1;
    public static final int PLAYER = 2;
    public static final int MONSTER = 4;
    public static final int VILLAGER = 8;
    public static final int ANIMAL = 16;
    public static final int OBJECT = 32;

    public static int getEntityGroup(Entity entity) {
        int entityGroup = 0;
        if (entity instanceof PlayerEntity) {
            entityGroup = EntityTargetGroup.PLAYER;
        }
        else if (entity instanceof HostileEntity
                || entity instanceof SlimeEntity
                || entity instanceof GhastEntity
                || entity instanceof PhantomEntity
                || entity instanceof EnderDragonEntity) {

            entityGroup = EntityTargetGroup.MONSTER;
        }
        else if (entity instanceof WanderingTraderEntity
                || entity instanceof VillagerEntity) {

            entityGroup = EntityTargetGroup.VILLAGER;
        }
        else if (entity instanceof AnimalEntity
                || entity instanceof WaterCreatureEntity
                || entity instanceof GolemEntity
                || entity instanceof AmbientEntity) {

            entityGroup = EntityTargetGroup.ANIMAL;
        }
        else if (entity instanceof ArmorStandEntity
                || entity instanceof PaintingEntity) {
            entityGroup = EntityTargetGroup.OBJECT;
        }
        return entityGroup | EntityTargetGroup.ALL;
    }
}
