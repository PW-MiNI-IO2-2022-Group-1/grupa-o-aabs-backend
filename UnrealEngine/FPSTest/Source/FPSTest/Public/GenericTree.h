// Fill out your copyright notice in the Description page of Project Settings.

#pragma once

#include "CoreMinimal.h"
#include "GameFramework/Actor.h"
#include "GenericTree.generated.h"

USTRUCT(BlueprintType)
struct FTreeComponentStruct
{
	GENERATED_BODY()
public:
	FTreeComponentStruct();

	UPROPERTY(EditAnywhere, BlueprintReadWrite, Category = "Tree Struct", meta = (ClampMin = "1"))
		double Height;

	UPROPERTY(EditAnywhere, BlueprintReadOnly, Category = "Tree Struct", meta = (ClampMin = "1"))
		double InitialSegmentSize;

	UPROPERTY(EditAnywhere, BlueprintReadOnly, Category = "Tree Struct", meta = (ClampMin = "1"))
		double HorizontalStretch;

	UPROPERTY(EditAnywhere, BlueprintReadWrite, Category = "Tree Struct", meta = (ClampMin = "1"))
		double SegmentSize;

	UPROPERTY(EditAnywhere, BlueprintReadWrite, Category = "Tree Struct", meta = (ClampMin = "1"))
		FVector2D Offset;

	UPROPERTY(EditAnywhere, BlueprintReadWrite, Category = "Tree Struct", meta = (ClampMin = "1"))
		double RotationFactor;

	UPROPERTY(EditAnywhere, BlueprintReadWrite, Category = "Tree Struct", meta = (ClampMin = "1"))
		double ScalingFactor;

	UPROPERTY(EditAnywhere, BlueprintReadWrite, Category = "Tree Struct")
		FVector2D Bounds;

	UPROPERTY(EditAnywhere, BlueprintReadWrite, Category = "Tree Struct")
		UStaticMesh* StaticMesh;

	UPROPERTY(EditAnywhere, BlueprintReadWrite, Category = "Tree Struct")
		UMaterial* Material;
};

UCLASS()
class FPSTEST_API AGenericTree : public AActor
{
	GENERATED_BODY()
	
public:	
	// Sets default values for this actor's properties

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
	UPROPERTY(EditAnywhere, BlueprintReadWrite, Category = "Tree variables")
		FTreeComponentStruct Trunk;

	UPROPERTY(EditAnywhere, BlueprintReadWrite, Category = "Tree variables", meta = (ClampMin = "1"))
		FTreeComponentStruct Treetop;

	UPROPERTY(EditAnywhere, BlueprintReadWrite, Category = "Tree variables")
		FRandomStream Stream;

	UPROPERTY(EditAnywhere, BlueprintReadOnly, Category = "Tree variables")
		int Seed;
private:

};
