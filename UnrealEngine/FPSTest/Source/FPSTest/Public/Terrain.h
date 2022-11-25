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
	static const inline double Scale = 100.0;
	static const inline double UVScale = 1.0;
	static const inline double Size = 20.0;

	int Permutation[256];

	ATerrain();
	void Initialize(int p[], int seed);

protected:
	virtual void BeginPlay() override;

private:
	UProceduralMeshComponent* ProceduralMesh;
	TArray<FVector> Vertices;
	TArray<int> Triangles;
	TArray<FVector2D> UV0;
	
	void CreateVertices();

	void CreateTriangles();
};
