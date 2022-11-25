// Fill out your copyright notice in the Description page of Project Settings.

#include "TerrainMenager.h"

ATerrainMenager::ATerrainMenager()
{
	PrimaryActorTick.bCanEverTick = true;
}

void ATerrainMenager::Move(FVector2D NewCenter)
{
	FVector2D dCenter = FVector2D(
		NewCenter.X - CenterRegion.X,
		NewCenter.Y - CenterRegion.Y
	);
	ATerrain*** NewRenderedTerrain = new ATerrain ** [Size];;
	for (int y = 0; y < Size; y++)
	{
		NewRenderedTerrain[y] = new ATerrain * [Size];
		for (int x = 0; x < Size; x++)
		{
			if (
				x + dCenter.X >= 0 &&
				x + dCenter.X < Size &&
				y + dCenter.Y >= 0 &&
				y + dCenter.Y < Size
				)
			{
				NewRenderedTerrain[y][x] = RenderedTerrain[y + (int)(dCenter.Y)][x + (int)(dCenter.X)];
			}
			else
			{
				FVector pos = FVector(
					(NewCenter.X + x - RenderDistance) * (ATerrain::Size * ATerrain::Scale),
					(NewCenter.Y + y - RenderDistance) * (ATerrain::Size * ATerrain::Scale),
					0);
				NewRenderedTerrain[y][x] = (ATerrain*)(GetWorld()->SpawnActor(
					ATerrain::StaticClass(),
					&pos));
				NewRenderedTerrain[y][x]->Initialize(Permutation, Seed);
			}
		}
	}
	for (int y = 0; y < Size; y++)
	{
		for (int x = 0; x < Size; x++)
		{
			if (
				x - dCenter.X < 0 ||
				x - dCenter.X >= Size ||
				y - dCenter.Y < 0 ||
				y - dCenter.Y >= Size
				)
				RenderedTerrain[y][x]->Destroy();
		}
		delete[] RenderedTerrain[y];
	}
	delete[] RenderedTerrain;
	RenderedTerrain = NewRenderedTerrain;
	CenterRegion = NewCenter;
}

// Called when the game starts or when spawned
void ATerrainMenager::BeginPlay()
{
	Super::BeginPlay();
	for (int i = 0; i < 256; i++)
	{
		Permutation[i] = i;
	}
	std::shuffle(&Permutation[0], &Permutation[255], std::default_random_engine(Seed));
	CenterRegion = FVector2D(0, 0);
	Size = 2 * RenderDistance + 1;
	RenderedTerrain = new ATerrain ** [Size];
	for (int y = 0; y < Size; y++)
	{
		RenderedTerrain[y] = new ATerrain * [Size];
		for (int x = 0; x < Size; x++)
		{
			FVector pos = FVector(
				(x - RenderDistance) * (ATerrain::Size * ATerrain::Scale), 
				(y - RenderDistance) * (ATerrain::Size * ATerrain::Scale), 
				0);
			FRotator rot = FRotator(0, 0, 0);
			RenderedTerrain[y][x] = (ATerrain*)(GetWorld()->SpawnActor(ATerrain::StaticClass(), &pos));
			RenderedTerrain[y][x]->Initialize(Permutation, Seed);
		}
	}
	FVector pos = FVector(
		500,
		200,
		100);
	Tree = (AGenericTree*)(GetWorld()->SpawnActor(AGenericTree::StaticClass(), &pos));
	Tree->Initialize(Seed, 20, 300, 40, 400);
}

// Called every frame
void ATerrainMenager::Tick(float DeltaTime)
{
	Super::Tick(DeltaTime);
	FVector pos = GetWorld()->GetFirstPlayerController()->GetPawn()->GetActorLocation();
	FVector2D reg = FVector2D(
		floor(pos.X / (ATerrain::Size * ATerrain::Scale)), 
		floor(pos.Y / (ATerrain::Size * ATerrain::Scale))
	);

	if (reg.X != CenterRegion.X || reg.Y != CenterRegion.Y)
	{
		Move(reg);
	}
}

