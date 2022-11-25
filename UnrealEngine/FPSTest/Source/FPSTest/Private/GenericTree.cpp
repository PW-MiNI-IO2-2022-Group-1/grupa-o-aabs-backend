// Fill out your copyright notice in the Description page of Project Settings.


#include "GenericTree.h"
#include "Math/RandomStream.h"
#include "UObject/UObjectGlobals.h"

// Sets default values
AGenericTree::AGenericTree()
{
 	// Set this actor to call Tick() every frame.  You can turn this off to improve performance if you don't need it.
	PrimaryActorTick.bCanEverTick = false;

	FRandomStream stream = FRandomStream(GetActorLocation().X + GetActorLocation().Y + GetActorLocation().Z);
	cubeMeshComponent = CreateDefaultSubobject<UStaticMeshComponent>(TEXT("Cube"));

	// Load the Cube mesh
	UStaticMesh* cubeMesh = ConstructorHelpers::FObjectFinder<UStaticMesh>(TEXT("StaticMesh'/Engine/BasicShapes/Cube.Cube'")).Object;
	UMaterial* mt = LoadObject<UMaterial>(nullptr, TEXT("/Game/StarterContent/Materials/M_Ground_Grass"));
	// Set the component's mesh
	cubeMeshComponent->SetStaticMesh(cubeMesh);
	cubeMeshComponent->SetMaterial(0, mt);

	TrunkHeight = stream.RandRange(20.0, 30.0);
	TreetopHeight = stream.RandRange(20.0, 30.0);
	TrunkStaticMesh = DuplicateObject<UStaticMeshComponent>(cubeMeshComponent, this);
	TrunkStaticMesh->SetMobility(EComponentMobility::Static);
	TreeStaticMesh = DuplicateObject<UStaticMeshComponent>(cubeMeshComponent, this);
	TreeStaticMesh->SetMobility(EComponentMobility::Static);
	//RenderTrunk();
	//RenderTreetop();
}

void AGenericTree::Initialize(int seed,/* UStaticMeshComponent* trunkStaticMesh, UStaticMeshComponent* treeStaticMesh,*/ int minTrunkHeight, int maxTrunkHeight, int minTreetopHeight, int maxTreetopHeight)
{
	FRandomStream stream = FRandomStream(seed + GetActorLocation().X + GetActorLocation().Y + GetActorLocation().Z);

	// Load the Cube mesh
	TrunkHeight = stream.RandRange(minTrunkHeight, maxTrunkHeight);
	TreetopHeight = stream.RandRange(minTreetopHeight, maxTreetopHeight);
	TrunkStaticMesh = DuplicateObject<UStaticMeshComponent>(cubeMeshComponent, this);
	TrunkStaticMesh->SetMobility(EComponentMobility::Static);
	TreeStaticMesh =  DuplicateObject<UStaticMeshComponent>(cubeMeshComponent, this);
	TreeStaticMesh->SetMobility(EComponentMobility::Static);
	RenderTrunk();
	RenderTreetop();
}

// Called when the game starts or when spawned
void AGenericTree::BeginPlay()
{
	Super::BeginPlay();
}

void AGenericTree::RenderTrunk()
{
	double currentHeight = Position.Z;
	double desiredTrunkHeight = Position.Z + TrunkHeight;
	double trunkSegmentHeight = TrunkStaticMesh->GetStaticMesh()->GetBoundingBox().GetSize().Z;
	FActorSpawnParameters spawnParams = FActorSpawnParameters();
	spawnParams.Template = (AActor*)(TrunkStaticMesh);
	do {
		auto newTrunkSegment = (GetWorld()->SpawnActor<UStaticMeshComponent>(FVector(Position.X, Position.Y, currentHeight), FRotator(), spawnParams));
		Trunk.Add(newTrunkSegment);
		currentHeight += trunkSegmentHeight;
	} while (currentHeight < desiredTrunkHeight);
}

void AGenericTree::RenderTreetop()
{

}


