// Fill out your copyright notice in the Description page of Project Settings.

#pragma once

#include "CoreMinimal.h"
#include "GameFramework/Actor.h"
#include "GenericTree.h"
#include "TreeManager.generated.h"

UCLASS()
class FPSTEST_API UTreeManager : public UObject
{
	GENERATED_BODY()
	
public:	
	// Sets default values for this actor's properties
	UTreeManager();
	void Initialize(int(&permutation)[256], FVector position, FVector2D terrainSize);
	void DestroyTrees();
private:	
	TArray<AGenericTree*> Trees;
	static UClass* treeClasses[];
};
