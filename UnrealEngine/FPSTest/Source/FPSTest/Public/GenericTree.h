// Fill out your copyright notice in the Description page of Project Settings.

#pragma once

#include "CoreMinimal.h"
#include "GameFramework/Actor.h"
#include "GenericTree.generated.h"

UCLASS()
class FPSTEST_API AGenericTree : public AActor
{
	GENERATED_BODY()
	
public:	
	// Sets default values for this actor's properties
	UPROPERTY(EditAnywhere, meta = (ClampMin = "1"))
		double TrunkSegmentSize;

	UPROPERTY(EditAnywhere, meta = (ClampMin = "1"))
		double TreetopSegmentSize;
	AGenericTree();
	virtual void Initialize(
		int seed,
		// UStaticMeshComponent* trunkStaticMesh,
		// UStaticMeshComponent* treeStaticMesh,
		int minTrunkHeight = 20,
		int maxTrunkHeight = 300,
		int minTreetopHeight = 40,
		int maxTreetopHeight = 400
	);

protected:
	// Called when the game starts or when spawned
	virtual void BeginPlay() override;

	virtual void RenderTrunk();
	virtual void RenderTreetop();

private:
	unsigned int TrunkHeight;
	unsigned int TreetopHeight;
	UStaticMeshComponent* TrunkStaticMesh;
	UStaticMeshComponent* TreeStaticMesh;
	UStaticMeshComponent* Root;
	TArray<UStaticMeshComponent*> Trunk;
	TArray<UStaticMeshComponent*> Treetop;

};
