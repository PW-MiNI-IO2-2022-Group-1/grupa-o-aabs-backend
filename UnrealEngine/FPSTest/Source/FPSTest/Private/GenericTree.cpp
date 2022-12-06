// Fill out your copyright notice in the Description page of Project Settings.


#include "GenericTree.h"
#include <cmath>
#include "Math/RandomStream.h"
#include "Engine/StaticMeshActor.h"
#include "UObject/UObjectGlobals.h"

class UInstancedStaticMeshComponent;

// Sets default values
AGenericTree::AGenericTree()
{
 	// Set this actor to call Tick() every frame.  You can turn this off to improve performance if you don't need it.
	Seed = 0;
	PrimaryActorTick.bCanEverTick = true;
	TrunkInit.InitialSegmentSize = 20.0;
	TrunkRender.RotationDegreesVariance = 15;
	TrunkRender.HorizontalScalingVariance = 0.05;
	TrunkInit.HeightBounds = FVector2D(100, 200);

	auto trunkStaticMesh = ConstructorHelpers::FObjectFinder<UStaticMesh>(TEXT("StaticMesh'/Engine/BasicShapes/Cube.Cube'"));
	if (trunkStaticMesh.Succeeded())  TrunkInit.StaticMesh = trunkStaticMesh.Object;
	TrunkInit.Material = LoadObject<UMaterial>(nullptr, TEXT("/Game/StarterContent/Materials/M_Wood_Walnut"));

	TreetopInit.InitialSegmentSize = 100.0;
	TreetopInit.VerticalOffsetPercentClamp = FVector2D(0.3, 0.7);
	TreetopRender.RotationDegreesVariance = 5;
	TreetopRender.HorizontalScalingVariance = 0.1;
	TreetopInit.HeightBounds = FVector2D(150, 300);

	auto treetopStaticMesh = ConstructorHelpers::FObjectFinder<UStaticMesh>(TEXT("StaticMesh'/Engine/BasicShapes/Cone.Cone'"));
	if (treetopStaticMesh.Succeeded())  TreetopInit.StaticMesh = treetopStaticMesh.Object;
	TreetopInit.Material = LoadObject<UMaterial>(nullptr, TEXT("/Game/StarterContent/Materials/M_Tech_Hex_Tile"));



	RootComponent = CreateDefaultSubobject<USceneComponent>(TEXT("Root"));
	
	TrunkRender.Instanced = CreateDefaultSubobject<UInstancedStaticMeshComponent>(TEXT("Trunk"));
	TreetopRender.Instanced = CreateDefaultSubobject<UInstancedStaticMeshComponent>(TEXT("Treetop"));

	TrunkRender.Instanced->AttachToComponent(RootComponent, FAttachmentTransformRules::KeepRelativeTransform);
	TreetopRender.Instanced->AttachToComponent(RootComponent, FAttachmentTransformRules::KeepRelativeTransform);

}

void AGenericTree::Initialize(FTreetopInit treetopInit, FTrunkInit trunkInit) {
	TrunkInit = trunkInit;
	TreetopInit = treetopInit;
}

void AGenericTree::Initialize(FTreetopRenderVariables treetopRender, FTrunkRenderVariables trunkRender)
{
	TrunkRender.HorizontalStretch = trunkRender.HorizontalStretch;
	TrunkRender.RotationDegreesVariance = trunkRender.RotationDegreesVariance;
	TrunkRender.HorizontalScalingVariance = trunkRender.HorizontalScalingVariance;

	TreetopRender.HorizontalStretch = treetopRender.HorizontalStretch;
	TreetopRender.RotationDegreesVariance = treetopRender.RotationDegreesVariance;
	TreetopRender.HorizontalScalingVariance = treetopRender.HorizontalScalingVariance;
}

void AGenericTree::Initialize(FTrunkRenderVariables trunkRender, FTreetopRenderVariables treetopRender)
{
	this->Initialize(treetopRender, trunkRender);
}

void AGenericTree::Initialize(FTrunkInit trunkInit, FTreetopInit treetopInit, FTreetopRenderVariables treetopRender, FTrunkRenderVariables trunkRender) {
	this->Initialize(treetopInit, trunkInit);
	this->Initialize(treetopRender, trunkRender);
}


double AGenericTree::GetRandomFromVector(FVector2D& bounds)
{
	return Stream.FRandRange(bounds.X, bounds.Y);
}

// get distance from bottom of a mesh (in scale) to center (render location)
double AGenericTree::GetMeshOffset(UStaticMesh* staticMesh, double size)
{
	if (staticMesh == nullptr) {
		return 0;
	}
	auto bounds = staticMesh->GetBoundingBox();
	return (-bounds.Min.Z * size) / bounds.GetSize().Z;
}

// Called when the game starts or when spawned
void AGenericTree::BeginPlay()
{
	Super::BeginPlay();
}

void AGenericTree::RenderUp(FTreeComponentRender& generatedStruct, FVector from, double Offset)
{
	if (generatedStruct.Instanced &&
		generatedStruct.Instanced->GetStaticMesh() &&
		generatedStruct.Instanced->GetMaterial(0)) {
		auto segmentHeight = generatedStruct.SegmentSize * Offset;
		auto segmentNumber = round(generatedStruct.Height / segmentHeight);
		auto translation = from;
		auto segmentScale = generatedStruct.SegmentSize / generatedStruct.Instanced->GetStaticMesh()->GetBoundingBox().GetSize().Z;
		TArray<FTransform> transforms;
		transforms.Empty(segmentNumber);
		generatedStruct.Instanced->ClearInstances();
		for (int i = 0; i < segmentNumber; i++) {

			auto rotation = FRotator(
				Stream.FRandRange(-generatedStruct.RotationDegreesVariance, generatedStruct.RotationDegreesVariance),
				Stream.FRandRange(-generatedStruct.RotationDegreesVariance, generatedStruct.RotationDegreesVariance),
				Stream.FRandRange(-generatedStruct.RotationDegreesVariance, generatedStruct.RotationDegreesVariance)
			);
			auto scale = FVector(
				generatedStruct.HorizontalStretch * Stream.FRandRange(1 - generatedStruct.HorizontalScalingVariance, 1 + generatedStruct.HorizontalScalingVariance),
				generatedStruct.HorizontalStretch * Stream.FRandRange(1 - generatedStruct.HorizontalScalingVariance, 1 + generatedStruct.HorizontalScalingVariance),
				1
			) * segmentScale;
			transforms.Add(FTransform(
				rotation,
				translation,
				scale
			));
			translation += FVector(0, 0, segmentHeight);
		}
		generatedStruct.Instanced->AddInstances(transforms, false);
	}
	
}

void AGenericTree::RenderTrunk(double offset)
{
	auto spawnOffset = FVector(0, 0, GetMeshOffset(TrunkInit.StaticMesh, TrunkRender.SegmentSize));
	RenderUp(
		TrunkRender,
		spawnOffset,
		offset
	);
}

void AGenericTree::RenderTreetop(double offset)
{
	auto spawnOffset = FVector(
		0, 
		0, 
		TrunkRender.Height + GetMeshOffset(TreetopInit.StaticMesh, TreetopRender.SegmentSize)
	);
	RenderUp(
		TreetopRender,
		spawnOffset,
		offset
	);
}

void AGenericTree::OnConstruction(const FTransform& transform)
{
	Stream = FRandomStream(
		Seed
		+ GetActorLocation().X
		+ GetActorLocation().Y
		+ GetActorLocation().Z
	);
	RenderTrunk(InitStruct(TrunkInit, TrunkRender));
	RenderTreetop(InitStruct(TreetopInit, TreetopRender));
	RootComponent->SetMobility(EComponentMobility::Movable);
}

double AGenericTree::InitStruct(FTreeComponentInit& init, FTreeComponentRender& render)
{
	if (init.VerticalOffsetPercentClamp.X < 0) init.VerticalOffsetPercentClamp.X = 0;
	if (init.VerticalOffsetPercentClamp.Y > 1) init.VerticalOffsetPercentClamp.Y = 1;
	render.Height = GetRandomFromVector(init.HeightBounds);
	auto offset = GetRandomFromVector(init.VerticalOffsetPercentClamp);
	int desiredSegmentNumber = round((render.Height - init.InitialSegmentSize) / (offset * init.InitialSegmentSize));
	render.SegmentSize = render.Height / (offset * desiredSegmentNumber + 1);
	if (init.StaticMesh) {
		render.Instanced->SetStaticMesh(init.StaticMesh);
	}
	if (init.Material) {
		render.Instanced->SetMaterial(0, init.Material);
	}

	return offset;
}


