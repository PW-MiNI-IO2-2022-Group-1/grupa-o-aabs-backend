// Fill out your copyright notice in the Description page of Project Settings.

#pragma once

#include "CoreMinimal.h"
#include "GameFramework/Actor.h"
#include "Terrain.generated.h"

class UProceduralMeshComponent;
class UMaterialInterface;

UCLASS()
class FPSTEST_API ATerrain : public AActor
{
	GENERATED_BODY()
	
public:	
	ATerrain();

protected:
	virtual void BeginPlay() override;

	UPROPERTY(EditAnywhere)
		UMaterialInterface* Material;


public:	
	virtual void Tick(float DeltaTime) override;

private:

	UProceduralMeshComponent* ProceduralMesh;

	TArray<FVector> Vertices;

	TArray<int> Triangles;

	TArray<FVector2D> UV0;
	
	void CreateVertices();

	void CreateTriangles();
};
