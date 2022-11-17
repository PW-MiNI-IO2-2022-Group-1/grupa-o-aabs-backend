// Fill out your copyright notice in the Description page of Project Settings.

#include "TerrainMenager.h"
#include <cmath>

// Sets default values
ATerrainMenager::ATerrainMenager()
{
 	// Set this actor to call Tick() every frame.  You can turn this off to improve performance if you don't need it.
	PrimaryActorTick.bCanEverTick = true;
	PrimaryActorTick.bStartWithTickEnabled = true;

}

void ATerrainMenager::Move(FVector2D NewCenter)
{
	int size = 2 * RenderDistance + 1;
	for (int y = 0; y < size; y++)
	{
		for (int x = 0; x < size; x++)
		{
			RenderedTerrain[y][x]->Destroy();
		}
	}
	for (int y = 0; y < size; y++)
	{
		for (int x = 0; x < size; x++)
		{
			FVector pos = FVector(
				(NewCenter.X + x - RenderDistance) * (ATerrain::Size * ATerrain::Scale), 
				(NewCenter.Y + y - RenderDistance) * (ATerrain::Size * ATerrain::Scale), 
				0);
			RenderedTerrain[y][x] = (ATerrain*)(GetWorld()->SpawnActor(
				ATerrain::StaticClass(), 
				&pos));
		}
	}
	CenterRegion = NewCenter;
}

// Called when the game starts or when spawned
void ATerrainMenager::BeginPlay()
{
	Super::BeginPlay();
	CenterRegion = FVector2D(0, 0);
	int size = 2 * RenderDistance + 1;
	RenderedTerrain = new ATerrain ** [size];
	for (int i = 0; i < size; i++)
	{
		RenderedTerrain[i] = new ATerrain * [size];
	}
	for (int y = 0; y < size; y++)
	{
		for (int x = 0; x < size; x++)
		{
			FVector pos = FVector(
				(x - RenderDistance) * (ATerrain::Size * ATerrain::Scale), 
				(y - RenderDistance) * (ATerrain::Size * ATerrain::Scale), 
				0);
			FRotator rot = FRotator(0, 0, 0);
			RenderedTerrain[y][x] = (ATerrain*)(GetWorld()->SpawnActor(ATerrain::StaticClass(), &pos));
		}
	}
	
}

// Called every frame
void ATerrainMenager::Tick(float DeltaTime)
{
	Super::Tick(DeltaTime);
	FVector pos = GetWorld()->GetFirstPlayerController()->GetPawn()->GetActorLocation();
	FVector2D reg = FVector2D(
		floor(pos.X / (ATerrain::Size * ATerrain::Scale)), 
		floor(pos.Y / (ATerrain::Size * ATerrain::Scale)));
	if (reg.X != CenterRegion.X || reg.Y != CenterRegion.Y)
	{
		Move(reg);
	}
}

