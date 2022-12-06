// Fill out your copyright notice in the Description page of Project Settings.


#include "Terrain.h"
#include "ProceduralMeshComponent.h"
#include <random>
#include <algorithm>
#include <cmath>
#include "TerrainMenager.h"
#include "PerlinNoise.h"

ATerrain::ATerrain()
{
	PrimaryActorTick.bCanEverTick = false;

	ProceduralMesh = CreateDefaultSubobject<UProceduralMeshComponent>("ProceduralMesh");
	ProceduralMesh->SetupAttachment(GetRootComponent());
	TreeManager = CreateDefaultSubobject<UTreeManager>(TEXT("Tree manager"));
}

void ATerrain::Initialize(int p[], int seed)
{
	for (int i = 0; i < 256; i++)
	{
		Permutation[i] = p[i];
	}
	CreateVertices();
	CreateTriangles();
}

void ATerrain::BeginPlay()
{
	Super::BeginPlay();
}

void ATerrain::OnConstruction(const FTransform& transform) {
	Super::OnConstruction(transform);

	ProceduralMesh->CreateMeshSection(0, Vertices, Triangles, TArray<FVector>(), UV0, TArray<FColor>(), TArray<FProcMeshTangent>(), true);
	UMaterial* mt = LoadObject<UMaterial>(nullptr, TEXT("/Game/StarterContent/Materials/M_Ground_Moss"));

	ProceduralMesh->SetMaterial(0, mt);
	TreeManager->Initialize(
		Permutation,
		GetActorLocation(),
		FVector2D(Size * Scale, Size * Scale)
	);
}

void ATerrain::Destroyed()
{
	if (TreeManager) {
		TreeManager->DestroyTrees();
	}
	Super::Destroyed();
}

void ATerrain::CreateVertices()
{
	for (int X = 0; X <= Size; ++X)
	{
		for (int Y = 0; Y <= Size; ++Y)
		{
			double n = PerlinNoise::Noise((GetActorLocation().X + (double)X * Scale) / (Size * Scale), 
				(GetActorLocation().Y + (double)Y * Scale) / (Size * Scale), Permutation) * 500;
			Vertices.Add(FVector(X * Scale, Y * Scale, n));
			UV0.Add(FVector2D(X * UVScale, Y * UVScale));
		}
	}
}

void ATerrain::CreateTriangles()
{
	int Vertex = 0;
	for (int X = 0; X < Size; ++X)
	{
		for (int Y = 0; Y < Size; ++Y)
		{
			Triangles.Add(Vertex);//Bottom left corner
			Triangles.Add(Vertex + 1);//Bottom right corner
			Triangles.Add(Vertex + Size + 1);//Top left corner
			Triangles.Add(Vertex + 1);//Bottom right corner
			Triangles.Add(Vertex + Size + 2);//Top right corner
			Triangles.Add(Vertex + Size + 1);//Top left corner

			++Vertex;
		}
		++Vertex;
	}
}

