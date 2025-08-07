import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterModule, ActivatedRoute, Router } from '@angular/router';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators, FormArray } from '@angular/forms';

import { Client, Address } from '../models/client.model';
import { ClientService } from '../services/client.service';

@Component({
  selector: 'app-client-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './client-form.component.html',
})
export class ClientFormComponent implements OnInit {
  form!: FormGroup;
  clientId?: number;
  isEditing = false;

  constructor(
    private fb: FormBuilder,
    private service: ClientService,
    private route: ActivatedRoute,
    public router: Router
  ) {}

  ngOnInit(): void {
    this.form = this.fb.group({
      name: ['', Validators.required],
      dateOfBirth: ['', Validators.required],
      fiscalId: ['', Validators.required],
      addresses: this.fb.array([]),
    });

const id = this.route.snapshot.paramMap.get('id');
if (id) {
  this.isEditing = true;
  this.clientId = +id;
  this.service.get(this.clientId).subscribe((client: Client) => {
    this.form.patchValue({
      name: client.name,
      dateOfBirth: client.dateOfBirth,
      fiscalId: client.fiscalId
    });
    client.addresses.forEach(addr => this.addAddress(addr));
  });
} else {
  this.addAddress();
}

  }

  get addresses(): FormArray {
    return this.form.get('addresses') as FormArray;
  }

  addAddress(address?: Address): void {
    this.addresses.push(
      this.fb.group({
        street: [address?.street || '', Validators.required],
        number: [address?.number || '', Validators.required],
        complement: [address?.complement || ''],
        postalCode: [address?.postalCode || '', Validators.required],
        council: [address?.council || '', Validators.required],
        district: [address?.district || '', Validators.required],
      })
    );
  }

  removeAddress(index: number): void {
    if (this.addresses.length > 1) {
      this.addresses.removeAt(index);
    }
  }

 submit(): void {
  const client = this.form.value as Client;

  const handleError = (error: any) => {
    const message = error?.error?.message || 'An error occurred';
    alert(message);
  };

  if (this.clientId) {
    this.service.update(this.clientId, client).subscribe({
      next: () => this.router.navigate(['/']),
      error: handleError
    });
  } else {
    this.service.create(client).subscribe({
      next: () => this.router.navigate(['/']),
      error: handleError
    });
  }
}


}
