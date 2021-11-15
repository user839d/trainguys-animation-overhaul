package com.trainguy.animationoverhaul.animations;

import com.trainguy.animationoverhaul.AnimationOverhaul;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;

import java.util.Objects;

public class LivingEntityAnimationController<T extends LivingEntity, L extends LivingEntityPartAnimator<T, M>, M extends EntityModel<T>> {

    private final L partAnimator;

    @SuppressWarnings("unchecked")
    public LivingEntityAnimationController(T livingEntity, M model){
        partAnimator = switch (livingEntity.getType().toString().split("\\.")[2]){
            case "player" -> (L) new PlayerPartAnimator<T, M>(livingEntity, model);
            default -> null;
        };
    }

    public void animate(){
        // Only animate the parts if the part animator exists
        if(partAnimator != null){
            partAnimator.initModel();
            partAnimator.adjustTimers();
            partAnimator.animateParts();
            partAnimator.finalizeModel();
        } else {

        }
    }
}