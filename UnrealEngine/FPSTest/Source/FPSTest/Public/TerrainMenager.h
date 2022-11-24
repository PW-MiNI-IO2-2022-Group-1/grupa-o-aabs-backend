// Fill out your copyright notice in the Description page of Project Settings.

#pragma once

#include "CoreMinimal.h"
#include "GameFramework/Actor.h"
#include "Terrain.h"
#include <cmath>
#include <random>
#include <algorithm>
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

	UPROPERTY(EditAnywhere)
		int Seed;

	void Move(FVector2D NewCenter);

protected:
	// Called when the game starts or when spawned
	virtual void BeginPlay() override;

public:	
	ATerrain*** RenderedTerrain;
	FVector2D CenterRegion;
	// Called every frame
	virtual void Tick(float DeltaTime) override;

private:

	int Permutation[256];
	int Size;
};
