// Fill out your copyright notice in the Description page of Project Settings.


#include "GenericTree.h"
#include <cmath>
#include "Math/RandomStream.h"
#include "UObject/UObjectGlobals.h"

FTreeComponentStruct::FTreeComponentStruct() {
	Height = 0;
	InitialSegmentSize = 20;
	HorizontalStretch = 1;
	Material = nullptr;
	StaticMesh = nullptr;
	Bounds = FVector2D(20, 100);
	Offset = FVector2D(1,1);
}

// Sets default values
AGenericTree::AGenericTree()
{
 	// Set this actor to call Tick() every frame.  You can turn this off to improve performance if you don't need it.
	Seed = 0;
	PrimaryActorTick.bCanEverTick = false;
	Trunk.SegmentSize = 20.0;
	Trunk.RotationFactor = 15;
	Trunk.ScalingFactor = 0.05;
	Treetop.SegmentSize = 100.0;
	Treetop.Offset = FVector2D(0.3, 0.7);
	Treetop.RotationFactor = 5;
	Treetop.ScalingFactor = 0.1;
	Trunk.Bounds = FVector2D(100, 200);
	Treetop.Bounds = FVector2D(150, 300);
	// TrunkStaticMesh = LoadObject<UStaticMesh>(nullptr, TEXT("/Game/StarterContent/Shapes/Shape_Cube"));
	// TrunkMaterial = LoadObject<UMaterial>(nullptr, TEXT("/Game/StarterContent/Materials/M_Wood_Walnut"));
	RootComponent = CreateDefaultSubobject<USceneComponent>(TEXT("Root"));
}

void AGenericTree::Initialize(
	int seed,
	// UStaticMeshComponent* trunkStaticMesh,
	// UStaticMeshComponent* treeStaticMesh,
	int minTrunkHeight,
	int maxTrunkHeight,
	int minTreetopHeight,
	int maxTreetopHeight)
{
	
	// TrunkStaticMesh = trunkStaticMesh;
	// TrunkMaterial = trunkMaterial;
	Trunk.Bounds = FVector2D(minTrunkHeight, maxTrunkHeight);
	Treetop.Bounds = FVector2D(minTreetopHeight, maxTreetopHeight);
}

// Called when the game starts or when spawned
void AGenericTree::BeginPlay()
{
	Super::BeginPlay();
}

void AGenericTree::RenderTrunk()
{
	//FVector pos = GetActorLocation();
	//double currentHeight = TrunkSegmentSize;

	//FActorSpawnParameters spawnParams = FActorSpawnParameters();
	//spawnParams.Template = Cast<AActor>(Root);

	//while (currentHeight < TrunkHeight) {
	//	auto newTrunkSegment = DuplicateObject<UStaticMeshComponent>(Root, this);

	//	if (newTrunkSegment != nullptr)
	//	{
	//		FVector newLocation = FVector(
	//			pos.X,
	//			pos.Y,
	//			pos.Z + currentHeight
	//		);

	//		if (GEngine != NULL && !newLocation.IsZero()) {
	//			// GEngine->AddOnScreenDebugMessage(-1, 5.0f, FColor::Red, FString(TEXT("Spawn tree trunk segment at ")).Append(newLocation.ToCompactString()));
	//		}
	//		newTrunkSegment->SetWorldLocation(newLocation);
	//		newTrunkSegment->RegisterComponent();
	//		Trunk.Add(newTrunkSegment);
	//		currentHeight += TrunkSegmentSize;
	//	}
	//	
	//}
}

void AGenericTree::RenderTreetop()
{

}


