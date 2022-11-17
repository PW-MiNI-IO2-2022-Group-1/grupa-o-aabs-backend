// Fill out your copyright notice in the Description page of Project Settings.

#pragma once

#include "CoreMinimal.h"
#include "GameFramework/Actor.h"
#include "Terrain.h"
#include "TerrainMenager.generated.h"

UCLASS()
class FPSTEST_API ATerrainMenager : public AActor
{
	GENERATED_BODY()
	
public:	
	// Sets default values for this actor's properties
	ATerrainMenager();

	UPROPERTY(EditAnywhere, meta=(ClampMin="1"))
		int RenderDistance;

	void Move(FVector2D NewCenter);

protected:
	// Called when the game starts or when spawned
	virtual void BeginPlay() override;

public:	
	// Called every frame
	virtual void Tick(float DeltaTime) override;

	ATerrain*** RenderedTerrain;

	FVector2D CenterRegion;
};
