// Fill out your copyright notice in the Description page of Project Settings.


#include "TreeManager.h"

// Sets default values
ATreeManager::ATreeManager()
{
 	// Set this actor to call Tick() every frame.  You can turn this off to improve performance if you don't need it.
	PrimaryActorTick.bCanEverTick = false;

}

// Called when the game starts or when spawned
void ATreeManager::BeginPlay()
{
	Super::BeginPlay();
	
}

// Called every frame
void ATreeManager::Tick(float DeltaTime)
{
	Super::Tick(DeltaTime);

}

