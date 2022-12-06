// Fill out your copyright notice in the Description page of Project Settings.


#include "TreeManager.h"
#include "SpruceLikeTree.h"
#include "Math/RandomStream.h"
#include "PerlinNoise.h"

// Sets default values
UTreeManager::UTreeManager() {
}


//put all the tree classes here
UClass* UTreeManager::treeClasses[] = {
	ASpruceLikeTree::StaticClass()
};

void UTreeManager::Initialize(int(&permutation)[256], FVector terrainPosition, FVector2D terrainSize)
{
	auto stream = FRandomStream(
		0 // Seed
		+ terrainPosition.X
		+ terrainPosition.Y
		+ terrainPosition.Z
	);
	auto treeNum = stream.RandRange(0, 3);
	for (int i = 0; i < treeNum; i++) {
		FVector position = stream.GetUnitVector();
		position.X *= terrainSize.X;
		position.Y *= terrainSize.Y;
		position += terrainPosition;
		position.Z = PerlinNoise::Noise(position.X / terrainSize.X, position.Y / terrainSize.Y, permutation) * 500;
		AGenericTree* tree = Cast<AGenericTree>(GetWorld()->SpawnActor<AGenericTree>(treeClasses[stream.RandRange(0, 0)], FTransform(position)));
		if (tree) {
			Trees.Add(tree);
		}
	}
}

void UTreeManager::DestroyTrees()
{
	for (AGenericTree* tree : Trees)
		tree->Destroy();
	Trees.Empty();
}

